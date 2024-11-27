const express = require('express');
const jsonwebtoken = require('jsonwebtoken');
const { hashSync, genSaltSync, compareSync } = require("bcrypt");
const cookieParser = require('cookie-parser');
const User = require('../models/user.model');

const initRoutes = express.Router();

const userRouter = require('./user.routes');

initRoutes.use(cookieParser());

initRoutes.post('/register', async (req, res, next) => {
    try {
        const userName = req.body.userName;
        const email = req.body.email;
        const role = req.body.role;
        let password = req.body.password;

        const old_user = await User.getUserByEmail(email);
        if (old_user) {
            return res.status(409).json({
                message: 'User already exists!'
            });
        }

        if (!userName || !email || !password || !role) {
            return res.sendStatus(400);
        }

        const salt = genSaltSync(10);
        password = hashSync(password, salt);

        const user = await User.insertUser(userName, email, password, role);

        const jsontoken = jsonwebtoken.sign({ user: user }, process.env.SECRET_KEY, { expiresIn: '1h' });
        res.cookie('token', jsontoken, { httpOnly: true, secure: true, SameSite: 'strict', expires: new Date(Number(new Date()) + 30 * 60 * 1000) }); //we add secure: true, when using https.

        res.json({ user, token: jsontoken });

        //return res.redirect('/mainpage');

    } catch (e) {
        console.log(e);
        res.sendStatus(400);
    }
});

initRoutes.post('/login', async (req, res, next) => {
    try {
        const email = req.body.email;
        const password = req.body.password;
        const user = await User.getUserByEmail(email);

        if (!user) {
            return res.status(401).json({
                message: "Invalid email or password"
            })
        }

        const isValidPassword = compareSync(password, user.password);
        if (isValidPassword) {
            user.password = undefined;
            const jsontoken = jsonwebtoken.sign({ user: user }, process.env.SECRET_KEY, { expiresIn: '1h' });
            res.cookie('token', jsontoken, { httpOnly: true, secure: true, SameSite: 'strict', expires: new Date(Number(new Date()) + 30 * 60 * 1000) }); //we add secure: true, when using https.

            res.json({ user, token: jsontoken });
            //return res.redirect('/mainpage') ;

        } else {
            return res.status(401).json({
                message: "Invalid email or password"
            });
        }

    } catch (e) {
        console.log(e);
    }
});

//  Verify Token
async function verifyTokenAndAdmin(req, res, next) {
    const token = req.cookies.token;
    console.log(token);

    if (token === undefined) {
        return res.status(403).json({
            message: "Access Denied! Unauthorized User"
        });
    } else {
        jsonwebtoken.verify(token, process.env.SECRET_KEY, (err, decoded) => {
            if (err) {
                res.json({
                    message: "Invalid Token..."
                });

            } else {
                const { user } = decoded;

                // Check if the decoded token contains user and role
                if (!user || !user.role) {
                    return res.status(403).json({
                        message: "Access Denied! Missing role information"
                    });
                }

                if (user.role === "admin") {
                    next();
                } else {
                    return res.status(403).res.json({
                        message: "Access Denied! you are not an Admin"
                    });

                }
            }
        })
    }
}

initRoutes.use('/user', verifyTokenAndAdmin, userRouter);


module.exports = initRoutes;