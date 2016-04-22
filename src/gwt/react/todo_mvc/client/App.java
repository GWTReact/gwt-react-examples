package gwt.react.todo_mvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react_router.client.ReactRouter;
import gwt.react_router.client.RouteProps;
import gwt.react_router.client.RouterProps;

import static gwt.react_router.client.ReactRouter.Route;
import static gwt.react_router.client.ReactRouter.Router;

public class App implements EntryPoint {

    static final int ESCAPE_KEY = 27;
    static final int ENTER_KEY = 13;

    static TodoModel model = new TodoModel();

    private void render() {
        ReactDOM.render(
            React.createElement(Router, new RouterProps().History(ReactRouter.hashHistory),
                    React.createElement(Route, new RouteProps().path("/").component(TodoList.component)),
                    React.createElement(Route, new RouteProps().path("/:nowShowing").component(TodoList.component))

            ),
            Document.get().getElementById("todoapp"));
    }

    @Override
    public void onModuleLoad() {

        model.subscribe(this::render);
        render();
    }
}