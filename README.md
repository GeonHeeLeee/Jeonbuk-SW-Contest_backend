# Jeonbuk-SW-Contest-backend: 전북 짹짹이
24년상반기 SW개발공모전(SW개발아이디어 및 결과물)
공모전 공식 링크 - https://www.contestkorea.com/sub/view.php?Txt_gbn=1&Txt_bcode=030510001&str_no=202401310029       

MySQL: 8.0.37    
Spring Boot: 3.2.3    
Java: 17    

DB 테이블 생성은 Spring Entity를 이용하여 application.properties에 있는 DB 연결 정보(url, username, password)를 수정 후, ContestApplication 실행 시 자동으로 테이블이 생성됩니다.         
또한 데이터 입력은 스프링에서 CSV를 불러와 저장됩니다. (현재는 ContestApplication에 주석 처리 되어 있습니다. 데이터 입력 시 주석 처리를 해제하고 ContestApplication을 실행하면 됩니다.)
