import React from 'react'
import { Route, Router, Switch } from 'react-router-dom'
import UserLoginPage from './routes/UserLoginPage'
import MenuPage from './routes/MenuPage'
import UserManagementPage from './routes/UserManagementPage'
import UserRegisterPage from './routes/UserRegisterPage'
import TablePage from './routes/TablePage'
import ReportPage from './routes/ReportPage'
import TableListPage from './routes/TableListPage'

export default function AppRouter() {
  return (
    <Router>
      <Switch>
        <Route path="/">
          <MenuPage/>
        </Route>
        <Route path="/user">
          <UserManagementPage/>
        </Route>
        <Route path="/user/login">
          <UserLoginPage/>
        </Route>
        <Route path="/user/register">
          <UserRegisterPage/>
        </Route>
        <Route path="/tables">
          <TableListPage/>
        </Route>
        <Route path="/tables/view">
          <TablePage/>
        </Route>
        <Route path="/tables/report">
          <ReportPage/>
        </Route>
      </Switch>
    </Router>
  )
}
