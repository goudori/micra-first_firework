package plugin.first_micra;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;


// gradle jarで実行する事で、作ったコードが、マイクラに反映される。
public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
//    thisは、Mainクラスの事
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer();
    World world = player.getWorld();
// 花火を３回打ち上げる
    for (int i = 0; i < 3; i++) {
      // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
      Firework firework = world.spawn(player.getLocation(), Firework.class);

      // 花火オブジェクトが持つメタ情報を取得。
      FireworkMeta fireworkMeta = firework.getFireworkMeta();

//      花火３種類
      FireworkEffect.Type type = null;

      Color color = null;

      switch (i) {
        case 0:
          type = Type.BALL;
          color = Color.RED;
          break;

        case 1:
          type = Type.CREEPER;
          color = Color.GREEN;
          break;

        case 2:
          type = Type.STAR;
          color = Color.YELLOW;
          break;
      }

      // メタ情報に対して設定を追加したり、値の上書きを行う。
      // 今回は青色で星型の花火を打ち上げる。
      fireworkMeta.addEffect(
          FireworkEffect.builder()
              .withColor(color)
              .with(type)
              .withFlicker()
              .build());
      fireworkMeta.setPower(0);

      // 追加した情報で再設定する。
      firework.setFireworkMeta(fireworkMeta);
    }

    player.sendTitle("マイクラの世界へようこそ!", "マインクラフト", 10, 70, 20);
  }
}
