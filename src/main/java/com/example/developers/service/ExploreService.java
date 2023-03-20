package com.example.developers.service;

import com.example.developers.DTO.ExploreDTO;
import com.example.developers.domain.Explore;
import com.example.developers.google.GoogleGeocodingService;
import com.example.developers.repository.ExploreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExploreService {
    private final ExploreRepository exploreRepository;

    private final GoogleGeocodingService googleGeocodingService;


    @Transactional
    public List<ExploreDTO> findAllConnecting() {
        List<Explore> explores = exploreRepository.findAll();

        return explores.stream()
                .map(Explore::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(ExploreDTO dto) {
        if(exploreRepository.findByName(dto.getName()).size() != 0) {
            List<Explore> find = exploreRepository.findByTag(dto.getTag());
            for(Explore explore : find) {
                if (dto.getName().equals(explore.getName())) {
                    log.info("@@@@@@@@@@@@@@@@@@@이미 존재하는 가게입니다.");
                    return;
                }
            }
        }
        exploreRepository.save(
                Explore.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .tele(dto.getTele())
                        .image(dto.getImage())
                        .tag(dto.getTag())
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .build()
        );
    }

    @Transactional
    public void crawlingSparrowClub() {
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
                    String[] isAddress = k.select("p.adress").html().split(" ");
                    if("머신샵".compareTo(k.select("a._fade_link em").html()) == 0
                            || isAddress.length <= 3
                            || "".compareTo(k.select("a div.thumb img").attr("src")) == 0 )
                        continue;
//
//
//                    System.out.println("title: "+k.select("div.tit").html());
//                    System.out.println("address: "+k.select("p.adress").html());
//                    System.out.println("phone: "+k.select("p.tell a").text().replaceAll("phone number",""));
//                    System.out.println("img: "+k.select("a div.thumb img").attr("src"));
//                    System.out.println("tag: "+k.select("a._fade_link em").html());
                    HashMap<String, Double> location;
                    try {
                        location =
                                (HashMap<String, Double>) googleGeocodingService.geocoding(k.select("p.adress").html());
                    }catch (Exception e){
                        log.info("Gecoding 오류 나옴~~~~~ "+ e);
                        continue;
                    }
                    System.out.println("lng = " + location.get("lng"));
                    System.out.println("lat = " + location.get("lat"));

                    save(ExploreDTO.builder()
                        .name(k.select("div.tit").html())
                        .address(k.select("p.adress").html())
                        .tele(k.select("p.tell a").text().replaceAll("phone number",""))
                        .image(k.select("a div.thumb img").attr("src"))
                        .tag(k.select("a._fade_link em").html())
                        .latitude(location.get("lat"))
                        .longitude(location.get("lng"))
                        .build());

//                    System.out.println("@@@@@@@@@@@@@@@@@@@");
                }
            }


            i++;
        }
    }
}
