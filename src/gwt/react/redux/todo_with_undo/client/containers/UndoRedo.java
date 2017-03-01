package gwt.react.redux.todo_with_undo.client.containers;

import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.redux.todo_with_undo.client.containers.FilterLink.Props;
import gwt.react.redux.todo_with_undo.client.reducers.TodoAppReducer;
import gwt.redux.client.addons.react_redux.MapDispatchToPropsFn;
import gwt.redux.client.addons.react_redux.MapStateToPropsFn;
import gwt.redux.client.addons.react_redux.ReactRedux;
import gwt.redux.client.addons.redux_undo.ReduxUndo;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$jsPlainObj;
import static gwt.react.client.api.React.DOM.button;
import static gwt.react.client.api.React.DOM.p;

/**
 * This class illustrates how to work with typeless props i.e. no prop class if defined for this component
 */
public class UndoRedo {

    private static StatelessComponent<BaseProps> undoRedoComp = (props) ->
        p(null,
            button(new BtnProps()
                    .onClick(props.getObj("onUndo"))
                    .disabled(!props.getBool("canUndo")),
                    "Undo"),
            button(new BtnProps()
                    .onClick(props.getObj("onRedo"))
                    .disabled(!props.getBool("canRedo")),
                    "Redo")
        );

    private static MapStateToPropsFn<TodoAppReducer.State, Props> mapStateToPropsFn = (state, ownProps) ->
            $jsPlainObj("canUndo", state.todos.past.getLength() > 0,
                     "canRedo", state.todos.future.getLength() > 0);

    private static MapDispatchToPropsFn<BaseProps> mapDispatchToProps = (dispatch, ownProps) -> {
        JsProcedure onUndo = () -> dispatch.forward(ReduxUndo.ActionCreators.undo());
        JsProcedure onRedo = () -> dispatch.forward(ReduxUndo.ActionCreators.redo());

        return $jsPlainObj("onUndo", onUndo, "onRedo", onRedo);
    };

    public static StatelessComponent<BaseProps> component =
            ReactRedux.connect(mapStateToPropsFn, mapDispatchToProps).toComponent(undoRedoComp);
}