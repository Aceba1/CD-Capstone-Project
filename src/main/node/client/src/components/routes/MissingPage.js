import React from 'react'
import Card from '../Card'
import BasicPage from './BasicPage'

export default function MissingPage() {
  return (
    <BasicPage className="page-404">
      <Card>
        <h1>404 - Page not found</h1>
      </Card>
    </BasicPage>
  )
}
