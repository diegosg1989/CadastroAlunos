import React, {useReducer, createContext} from 'react'
export const AlunoContext = createContext()

const initialState ={
    alunos: []
}

const reducer = (state,action) =>{
    switch (action.type){
        case "ADD_ALUNO":
            return{
                alunos: [...state.alunos, action.payload]
            }
        default:
            throw new Error()    
    }
}

export const AlunoContextProvider = (props) =>{
    const [state, dispatch] = useReducer(reducer,initialState)
    return(
        <AlunoContext.Provider value={[state,dispatch]}>
            {props.children}
        </AlunoContext.Provider>
    )
}