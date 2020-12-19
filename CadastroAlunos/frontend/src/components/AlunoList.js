import React, {useContext} from 'react'
import {AlunoContext} from '../store/store'
import {Col, Table, Alert} from 'reactstrap'

export default()=>{
    var style1 = {
        backgroundColor: '#efefef'
    }

    const [state] = useContext(AlunoContext);
    const rows = state.alunos.map((aluno, index) => 
        <tr key={index}>
            <td>{aluno.nome}</td>
            <td>{aluno.idade}</td>
            <td>{aluno.serie}</td>
        </tr>
    )
    if (state.alunos.length > 0) {
        return (
            <Col md="8">
                <Table striped style={style1}>
                    <thead>
                        <tr>
                            <td>Nome</td>
                            <td>Idade</td>
                            <td>Série</td>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </Table>
            </Col>
        )
    } else {
        return (
            <Col md="8">
                <Alert color="danger">Nenhum aluno cadastrado no momento.</Alert>
            </Col>
        )
    }
}