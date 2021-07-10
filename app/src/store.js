import { applyMiddleware, createStore } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from './reducers';

const initialState = {};

let store = createStore(rootReducer, initialState, applyMiddleware(thunk));

export default store;
