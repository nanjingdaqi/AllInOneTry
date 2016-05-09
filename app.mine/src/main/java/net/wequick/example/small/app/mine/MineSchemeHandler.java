package net.wequick.example.small.app.mine;

import android.content.Intent;
import android.widget.Toast;
import me.ele.webview.scheme.IScheme;
import me.ele.webview.scheme.Scheme;
import me.ele.webview.scheme.SchemeHandler;

@SchemeHandler("mine://test")
public class MineSchemeHandler implements IScheme {
  @Override public void doWithScheme(Scheme scheme) throws Exception {
    Toast.makeText(scheme.getContext(), "invoke mine test scheme handler", Toast.LENGTH_SHORT).show();
    scheme.getContext().startActivity(new Intent(scheme.getContext(), MainActivity.class));
  }
}
