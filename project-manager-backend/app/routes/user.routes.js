const express = require('express');
const userRouter = express.Router();
const user = require("../controllers/user.controller.js");

userRouter.get('/:userId', (req, res, next) => {
    res.status(200).json({ user: req.user });
});

userRouter.post('/', user.createUser);

userRouter.put('/:id', user.updateUser);

userRouter.delete('/:id', user.deleteUser);

userRouter.get('/', user.findAll);

module.exports = userRouter;