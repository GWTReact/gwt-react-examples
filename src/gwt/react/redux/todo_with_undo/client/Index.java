package gwt.react.redux.todo_with_undo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.redux.todo_with_undo.client.components.App;
import gwt.react.redux.todo_with_undo.client.reducers.TodoAppReducer;
import gwt.redux.client.Redux;
import gwt.redux.client.Store;
import gwt.redux.client.addons.react_redux.ProviderProps;
import gwt.redux.client.addons.react_redux.ReactRedux;

public class Index implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Store<TodoAppReducer.State> store = Redux.createStore(TodoAppReducer.todoApp);

        ReactDOM.render(
            React.createElement(ReactRedux.Provider, new ProviderProps().Store(store),
                React.createElement(App.component, null)
            ),
            Document.get().getElementById("root"));
    }
}