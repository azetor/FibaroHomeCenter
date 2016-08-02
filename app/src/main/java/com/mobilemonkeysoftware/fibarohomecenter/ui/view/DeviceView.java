package com.mobilemonkeysoftware.fibarohomecenter.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.mobilemonkeysoftware.fibaroapi.model.DeviceType;
import com.mobilemonkeysoftware.fibarohomecenter.R;

import butterknife.BindView;

/**
 * Created by AR on 02/08/2016.
 */
public class DeviceView extends BaseView {

    public interface OnDeviceChangeListener {
        void onTurnOnChanged(View view, boolean checked);
        void onProgressChanged(View view, int level);
    }

    @BindView(R.id.name) TextView name;
    @BindView(R.id.type) TextView type;
    @BindView(R.id.switch_on_off) Switch switchOnOff;
    @BindView(R.id.level) SeekBar level;

    private OnDeviceChangeListener mListener;

    public DeviceView(Context context) {
        super(context);
    }

    public DeviceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeviceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public View onInitialize() {
        return inflate(getContext(), R.layout.view_device, null);
    }

    @Override public void initialize() {
        super.initialize();

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (mListener != null) {
                    mListener.onTurnOnChanged(DeviceView.this, checked);
                }
            }
        });
        level.setMax(100);
        level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int level, boolean checked) {
                if (mListener != null) {
                    mListener.onProgressChanged(DeviceView.this, level);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void setName(@NonNull String name) {
        this.name.setText(String.format("Name: %s", name));
    }

    public void setType(@NonNull DeviceType type) {
        this.type.setText(String.format("Type: %s", type.name()));
        level.setVisibility(DeviceType.DIMMABLE_LIGHT == type ? VISIBLE : GONE);
    }

    public void setTurn(boolean on) {
        switchOnOff.setChecked(on);
    }

    public void setLevel(int level) {
        this.level.setProgress(level);
    }

    public void setOnDeviceChangeListener(@NonNull OnDeviceChangeListener listener) {
        mListener = listener;
    }

}
