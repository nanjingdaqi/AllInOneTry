package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CameraStateButton extends ImageView {

	private State state;
	
	public CameraStateButton(Context context) {
		super(context);
	}

	public CameraStateButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CameraStateButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void changeState(State state) {
		setImageDrawable(getResources().getDrawable(state.imageId));
		state.stateChangedListener.stateChanged(state);
		this.state = state;
	}
	
	@Override
	public void setImageDrawable(Drawable drawable) {
		super.setImageDrawable(new AutoChangeStateDrawable(drawable));
	}
	
	public int getImageResource() {
		if (state != null) {
			return state.imageId;
		}
		return -1;
	}
	
	public static class State {
		int imageId;
		public interface StateChangedListener {
			void stateChanged(State preState);
		}
		StateChangedListener stateChangedListener;

		public State(int imageId, StateChangedListener stateChangedListener) {
			super();
			this.stateChangedListener = stateChangedListener;
			this.imageId = imageId;
		}
		
	}

	/**
	 * The stateful LayerDrawable used by this button.
	 */
	protected class AutoChangeStateDrawable extends LayerDrawable {

		protected int pressedAlpha = 153;
		protected int fullAlpha = 255;

		public AutoChangeStateDrawable(Drawable d) {
			super(new Drawable[] { d });
		}

		@Override
		protected boolean onStateChange(int[] states) {
			boolean pressed = false;

			for (int state : states) {
				if (state == android.R.attr.state_pressed) {
					pressed = true;
				}
			}

			mutate();
			if (pressed) {
				setAlpha(pressedAlpha);
			} else {
				setAlpha(fullAlpha);
			}
			invalidateSelf();

			return super.onStateChange(states);
		}

		@Override
		public boolean isStateful() {
			return true;
		}
	}
}
