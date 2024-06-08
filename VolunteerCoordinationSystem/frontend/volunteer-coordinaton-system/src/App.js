import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import VolunteerDashboard from './components/VolunteerDashboard';
import OrganizationDashboard from './components/OrganizationDashboard';

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <h1>Volunteer Coordination System</h1>
        </header>
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/volunteer-dashboard" component={VolunteerDashboard} />
          <Route path="/organization-dashboard" component={OrganizationDashboard} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
