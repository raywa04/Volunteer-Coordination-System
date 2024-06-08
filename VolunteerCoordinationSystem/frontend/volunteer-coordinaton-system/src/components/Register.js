import React, { useState } from 'react';
import axios from 'axios';

function Register() {
  const [user, setUser] = useState({ username: '', password: '', role: 'volunteer' });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('/register', user).then((response) => {
      // Handle successful registration
    }).catch((error) => {
      // Handle registration error
    });
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input type="text" name="username" value={user.username} onChange={handleChange} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" name="password" value={user.password} onChange={handleChange} />
        </div>
        <div>
          <label>Role:</label>
          <select name="role" value={user.role} onChange={handleChange}>
            <option value="volunteer">Volunteer</option>
            <option value="organization">Organization</option>
          </select>
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
