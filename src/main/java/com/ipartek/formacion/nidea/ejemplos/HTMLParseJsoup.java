package com.ipartek.formacion.nidea.ejemplos;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParseJsoup {
	public static void main(String[] args) throws IOException {

		String url = "http://192.168.0.15:8080/nidea/login";

		Connection.Response response = Jsoup.connect(url).method(Connection.Method.POST).data("usuario", "admin")
				.data("password", "admin").execute();
		Document doc3 = response.parse();
		System.out.println(doc3.getElementsByTag("h1").get(0).text());

		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println("Titulo: " + title);

		Elements anclas = doc.getElementsByTag("a");
		for (Element ancla : anclas) {

			String urlPagina2 = ancla.attr("href");
			Document doc2 = Jsoup.connect(urlPagina2).get();

			System.out.println("Titulo: " + doc2.title());
		}

	}

}
