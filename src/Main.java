import dao.NationalDAO;
import dao.PlayerDAO;
import model.National;
import model.Player;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PlayerDAO playerDAO = new PlayerDAO();
        NationalDAO nationalDAO = new NationalDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========== MENU ==========");
            System.out.println("1. Thêm National");
            System.out.println("2. Xóa National");
            System.out.println("3. Xem tất cả National");
            System.out.println("4. Thêm Player");
            System.out.println("5. Xóa Player");
            System.out.println("6. Hiển thị tất cả Player");
            System.out.println("7. Tìm Player theo tên");
            System.out.println("8. Hiển thị Top 10 Player có HighScore cao nhất");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên National: ");
                    String natName = sc.nextLine();
                    nationalDAO.insertNational(new National(0, natName));
                    break;
                case 2:
                    System.out.print("Nhập ID National muốn xóa: ");
                    int idDelNat = Integer.parseInt(sc.nextLine());
                    nationalDAO.deleteNational(idDelNat);
                    break;
                case 3:
                    List<National> listNat = nationalDAO.getAllNational();
                    for (National n : listNat) {
                        System.out.println(n.getNationalId() + " - " + n.getNationalName());
                    }
                    break;
                case 4:
                    System.out.print("Nhập NationalId: ");
                    int natId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập Player Name: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập High Score: ");
                    int score = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập Level: ");
                    int level = Integer.parseInt(sc.nextLine());
                    Player p = new Player(natId, name, score, level);
                    playerDAO.insertPlayer(p);
                    break;
                case 5:
                    System.out.print("Nhập PlayerId muốn xóa: ");
                    int idDel = Integer.parseInt(sc.nextLine());
                    playerDAO.deletePlayer(idDel);
                    break;
                case 6:
                    List<Player> listAll = playerDAO.displayAll();
                    System.out.println("PlayerId | PlayerName | HighScore | Level | NationalId");
                    for (Player pl : listAll) {
                        System.out.println(pl.getPlayerId() + " | " + pl.getPlayerName()
                                + " | " + pl.getHighScore() + " | " + pl.getLevel()
                                + " | " + pl.getNationalId());
                    }
                    break;
                case 7:
                    System.out.print("Nhập tên Player cần tìm: ");
                    String keyword = sc.nextLine();
                    List<Player> listFind = playerDAO.displayByName(keyword);
                    for (Player pl : listFind) {
                        System.out.println(pl.getPlayerId() + " - " + pl.getPlayerName());
                    }
                    break;
                case 8:
                    List<Player> listTop = playerDAO.displayTop10();
                    for (Player pl : listTop) {
                        System.out.println(pl.getPlayerName() + " - " + pl.getHighScore());
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}
