import React, {Dispatch} from 'react'
import './assets/css/App.css'
import Nav from './components/nav/Nav'
import SlideArea from './components/canvasArea/CanvasArea'
import {connect} from "react-redux"
import {initialState} from "./store/localStorage"
import {Editor} from "./entities/Editor"
import {canRedo, canUndo, canUndoKeyboard} from "./store/stateHistory"
import {useDragAndDrop} from "./customHooks/useDragAndDrop"
import {useEventListener} from "./customHooks/useEventListner"
import {DELETE_ELEMENTS, REDO, SET_EDITOR, UNDO} from "./store/actionTypes"
import {changePrimitiveStyleMenu} from "./functions/changePrimitiveStyleMenu"


const mapStateToProps = (state: Editor) => ({
    state: state
})


const mapDispatchToProps = (dispatch: Dispatch<any>) => ({
    setEditor: (state: Editor = initialState) => dispatch({type: SET_EDITOR, payload: state}),
    undo: () => dispatch({type: UNDO}),
    redo: () => dispatch({type: REDO}),
    deleteElements: () => {
        dispatch({type: DELETE_ELEMENTS})
        changePrimitiveStyleMenu(false)
    },
})


const App = (props: any) => {
    if (Object.keys(props.state).length === 0) props.setEditor()

    const handleUndoRedo = (evt: KeyboardEvent) => {
        if (evt.ctrlKey && evt.shiftKey && evt.keyCode === 90) {
            if (canRedo()) {
                props.redo()
            }
        } else if (canUndoKeyboard(evt)) {
            if (canUndo()) {
                props.undo()
            }
        }

        if (evt.keyCode === 46) {
            props.deleteElements()
        }
    }
    useEventListener('keydown', handleUndoRedo)

    useDragAndDrop()

    return (
        <div className="wrapper">
            <Nav/>
            <div className="main-block">
                <SlideArea/>
            </div>
        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(App)
