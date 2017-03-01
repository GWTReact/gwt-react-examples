package gwt.react.mobx.todo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import gwt.mobx.client.MobX;
import gwt.mobx.client.MobXDevTools;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.mobx.todo.client.components.App;
import gwt.react.mobx.todo.client.components.AppStateProps;
import gwt.react.mobx.todo.client.state.AppState;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;
import static gwt.react.client.api.React.DOM.div;

public class Index implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AppState appState = new AppState();

        //Force the use of actions
        MobX.useStrict(true);

        ReactDOM.render(
            div(null,
                //Show Dev tools toolbar
                React.createElement(MobXDevTools.component, $(new BaseProps(), "hightlightTimeout", 4000)),
                React.createElement(App.class, $(new AppStateProps(), "appState", appState))
            ),
            Document.get().getElementById("mainCont"));
    }
}
