/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.state;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public interface ChangesState {

    public void changeState(State state);
    public State getState();
    
}
