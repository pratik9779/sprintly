const User = require('../models/user.model');
const { hashSync, genSaltSync, compareSync } = require("bcrypt");

exports.createUser = async (req, res, next) => {
    try {
        const userName = req.body.user.userName;
        const email = req.body.user.email;
        const role = req.body.user.role;
        let password = req.body.user.password;


        if (!userName || !email || !password || !role) {
            return res.sendStatus(400);
        }

        const salt = genSaltSync(10);
        password = hashSync(password, salt);

        const user = await User.insertUser(userName, email, password, role);
        res.json({ user: user });


    } catch (e) {
        console.log(e);
        res.sendStatus(400);
    }
}

exports.updateUser = async (req, res, next) => {
    try {
        const userName = req.body.user.userName;
        const role = req.body.user.role;
        const email = req.body.user.email;
        let password = req.body.user.password;
        const userId = req.params.id;

        if (!userName || !role || !email || !password) {
            return res.sendStatus(400);
        }

        const salt = genSaltSync(10);
        password = hashSync(password, salt);

        const user = await User.updateUser(userName, role, email, password, userId);
        res.json({ message: "user updated" });


    } catch (e) {
        console.log(e);
        res.sendStatus(400);
    }
}

exports.deleteUser = async (req, res, next) => {
    try {
        const userId = req.params.id
        const user = await User.deleteUser(userId);
        return res.sendStatus(204);

    } catch (e) {
        console.log(e);
        res.sendStatus(400);
    }
}

exports.findAll = async (req, res, next) => {
    try {
        const users = await User.allUser();
        res.json({ users: users });
    } catch (e) {
        console.log(e);
    }
}