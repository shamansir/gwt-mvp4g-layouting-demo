/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui.state;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public interface ChangesState {

    public void changeState(State state);
    public State getState();
    
}
