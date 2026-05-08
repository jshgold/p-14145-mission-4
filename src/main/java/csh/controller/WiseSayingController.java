package csh.controller;

import csh.entity.WiseSaying;
import csh.service.WiseSayingService;

import java.util.Map;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService service = new WiseSayingService();
    private final Scanner sc;
    public WiseSayingController(Scanner scanner) {
        sc = scanner;
    }

    public void requestCreate() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        int id = service.create(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
    }

    public void requestShowList() {
        Map<Integer, WiseSaying> map = service.getList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(WiseSaying ws : map.values()) {
            System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent());
        }
    }

    public void requestUpdate(int id) {
        WiseSaying ws = service.getWiseSayingById(id);
        if(ws == null) {
            System.out.println("해당 명언은 존재 하지않습니다.");
            return;
        }
        System.out.println("명언(기존) : %s".formatted(ws.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.println("저자(기존) : %s".formatted(ws.getAuthor()));
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        service.update(id, content, author);
        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    public void requestDelete(int id) {
        boolean flag = service.deleteWiseSayingById(id);
        if(flag) System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
        else System.out.println("해당 명언은 존재하지않습니다.");
    }
}
