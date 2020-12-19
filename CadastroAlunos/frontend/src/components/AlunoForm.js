import React, { useContext, useState } from 'react'
import { AlunoContext } from '../store/store'
import { Col, Form, FormGroup, Label, Input, Button } from 'reactstrap'

export default () => {
    const nome = useFormInput("")
    const idade = useFormInput("")
    const serie = useFormInput("")
    
    // eslint-disable-next-line no-unused-vars
    const [state, dispatch] = useContext(AlunoContext)

    const request = (data) => {
        fetch('http://localhost:8081/v1/alunos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
            .then(response => {
                console.log("Data Saved");
            });
    }

    const onSubmit = event => {

        const data = {
            nome: nome.value, 
            idade: idade.value, 
            serie: serie.value
        }

        event.preventDefault()
        dispatch({
            type: "ADD_ALUNO",
            payload: data
        })

        request(data);
    };



    return (
        <Col className="form" md="4">
            <Form onSubmit={onSubmit}>
                <FormGroup>
                    <Label>Nome:</Label>
                    <Input {...nome} type="text" name="nome" required autoFocus />
                </FormGroup>
                <FormGroup>
                    <Label>Idade:</Label>
                    <Input {...idade} type="text" name="idade" required />
                </FormGroup>
                <FormGroup>
                    <Label>Série:</Label>
                    <Input {...serie} type="text" name="serie" required />
                </FormGroup>
                <Button color="primary" size="lg" block>Adicionar</Button>
            </Form>
        </Col>
    )
}

function useFormInput(initialValue) {
    const [value, setValue] = useState(initialValue)
    const handleChange = e => {
        setValue(e.target.value)
    }
    return {
        value,
        onChange: handleChange
    }
}