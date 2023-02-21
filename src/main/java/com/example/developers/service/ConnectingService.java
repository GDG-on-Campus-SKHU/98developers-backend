package com.example.developers.service;

import com.example.developers.DTO.ConnectingDTO;
import com.example.developers.domain.Connecting;
import com.example.developers.repository.ConnectingRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ConnectingService {
    private final ConnectingRepository connectingRepository;

    @Transactional
    public void save(ConnectingDTO dto){
        if(connectingRepository.findByStore(dto.getStore()).size() != 0) {
            List<Connecting> find = connectingRepository.findByTag(dto.getTag());
            for(Connecting connecting : find)
                if (dto.getStore() == connecting.getStore())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"이미 존재하는 가게입니다.");

            System.out.println("@@@@@@@@@@@@@@@@@@@이미 존재하는 가게입니다.");
        }
        connectingRepository.save(
                Connecting.builder()
                        .store(dto.getStore())
                        .location(dto.getLocation())
                        .phone_number(dto.getPhone_number())
                        .image(dto.getImage())
                        .tag(dto.getTag())
                        .build()
        );
    }

    @Transactional
    public void crawlingSparrowClub(){
        int i = 1;
        while(true) {
            String url = "https://ppseoul.com/map/?sort=TIME&keyword_type=all&page="+ i;
            Connection conn = Jsoup.connect(url);
            //Jsoup 커넥션 생성

            Document document = null;
            try {
                document = conn.get();
                //url의 내용을 HTML Document 객체로 가져온다.
                //https://jsoup.org/apidocs/org/jsoup/nodes/Document.html 참고
            } catch (IOException e) {
                e.printStackTrace();
                return ;
            }

            Elements selectsNavi = document.select("nav.map-inner ul.pagination li.active");
            if (Integer.parseInt(selectsNavi.text()) != i)
                break;

            System.out.println("selectsNavi = " + selectsNavi.text());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^리얼 있음 = "+ i + "번째임 리얼");



            Elements selects = document.select("div.map-list-wrap");
            //select 메서드 안에 css selector를 작성하여 Elements를 가져올 수 있다.

            for (Element select : selects) {
                for(Element k : select.select("div.map_container")) {


//                    System.out.println("@@@@@@@@@@select = ");
//
//
//                    System.out.println("title: "+k.select("div.tit").html());
//                    System.out.println("address: "+k.select("p.adress").html());
//                    System.out.println("phone: "+k.select("p.tell a").text().replaceAll("phone number",""));
//                    System.out.println("img: "+k.select("a div.thumb img").attr("src"));
//                    System.out.println("tag: "+k.select("a._fade_link em").html());


                save(ConnectingDTO.builder()
                        .store(k.select("div.tit").html())
                        .location(k.select("p.adress").html())
                        .phone_number(k.select("p.tell a").text().replaceAll("phone number",""))
                        .homepage("")
                        .image(k.select("a div.thumb img").attr("src"))
                        .tag(k.select("a._fade_link em").html())
                        .build());

//                    System.out.println("@@@@@@@@@@@@@@@@@@@");
                }
            }


            i++;
        }
    }
}
