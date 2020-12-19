import React from 'react';
import './styles/App.css';
import { Container, Row, Col } from 'reactstrap'
import AlunoForm from './components/AlunoForm'
import AlunoList from './components/AlunoList'
import { AlunoContextProvider } from './store/store'


export default () => {
  return (
    <AlunoContextProvider>
      <h1 className="title">API - Alunos</h1>
      <Container>
        <Row className="justify-content-center">
          <Col md="12">
            <h1><strong>Cadastramento de Alunos</strong></h1>
            <hr className="hr" />
          </Col>
          <AlunoForm />
          <AlunoList />
        </Row>
      </Container>
    </AlunoContextProvider> 
  )
}