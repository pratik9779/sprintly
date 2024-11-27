const sql = require("./db.js");

const User = {};

User.allUser = () => {
    return new Promise((resolve, reject) => {
        sql.query('SELECT * FROM users_configuration ', (error, users) => {
            if (error) {
                return reject(error);
            }
            return resolve(users);
        });
    });
};

User.getUserByEmail = (email) => {
    return new Promise((resolve, reject) => {
        sql.query('SELECT * FROM users_configuration WHERE email = ?', [email], (error, users) => {
            if (error) {
                return reject(error);
            }
            return resolve(users[0]);
        });
    });
};

User.insertUser = (userName, email, password, role) => {
    return new Promise((resolve, reject) => {
        sql.query('INSERT INTO users_configuration (username, email, password, role) VALUES (?,  ?, ?, ?)', [userName, email, password, role], (error, result) => {
            if (error) {
                return reject(error);
            }

            return resolve({ userName, email, password, role });
        });
    });
};

User.updateUser = (userName, role, email, password, id) => {
    return new Promise((resolve, reject) => {
        sql.query('UPDATE users_configuration SET username = ?, role= ?, email= ?, password=? WHERE id = ?', [userName, role, email, password, id], (error) => {
            if (error) {
                return reject(error);
            }

            return resolve();
        });
    });
};

User.deleteUser = (id) => {
    return new Promise((resolve, reject) => {
        sql.query('DELETE FROM users_configuration WHERE id = ?', [id], (error) => {
            if (error) {
                return reject(error);
            }
            return resolve(console.log("User deleted"));
        });
    });
};


module.exports = User