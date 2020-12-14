import React from 'react'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import UserLoginPage from './routes/UserLoginPage'
import MenuPage from './routes/MenuPage'
import UserManagementPage from './routes/UserManagementPage'
import UserRegisterPage from './routes/UserRegisterPage'
import TablePage from './routes/TablePage'
import ReportPage from './routes/ReportPage'
import TableListPage from './routes/TableListPage'
import MissingPage from './routes/MissingPage'

export default function AppRouter() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact>
          <MenuPage/>
        </Route>
        <Route path="/user" exact>
          <UserManagementPage/>
        </Route>
        <Route path="/user/login">
          <UserLoginPage/>
        </Route>
        <Route path="/user/register">
          <UserRegisterPage/>
        </Route>
        <Route path="/tables" exact>
          <TableListPage/>
        </Route>
        <Route path="/tables/report">
          <ReportPage/>
        </Route>
        <Route path="/tables/view">
          <TablePage/>
        </Route>
        <Route >
          <MissingPage/>
        </Route>
      </Switch>
    </BrowserRouter>
  )
}
