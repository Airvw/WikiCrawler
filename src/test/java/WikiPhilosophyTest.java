import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WikiPhilosophyTest {

    private String url;
    private Connection conn;

    private Document document;

    private Element element;

    @BeforeEach
    void setUp() throws IOException {
        url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        conn = Jsoup.connect(url);
        document = conn.get();
        element = document.getElementById("mw-content-text");
    }

    @Test
    @DisplayName("다운로드_테스트")
    void downloadPageTest() {
        assertThat(element.tagName()).isEqualTo("div");
        assertThat(element.parent().id()).isEqualTo("bodyContent");
    }

    @Test
    @DisplayName("링크_테스트")
    void findLinkTest(){
        Elements paragraphs = element.getElementsByTag("p");
        Element paragraph = paragraphs.get(1);
        Elements links = paragraph.getElementsByTag("a");
        Element link = links.get(0);
        assertThat(link.tagName()).isEqualTo("a");
        assertThat(link.text()).isEqualTo("high-level");
    }
}
