package news.crawler.service.executer;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domin.SourceConfig;

import java.util.List;

@Slf4j
public class ExecDevBy implements Execute{

    @Override
    public List<EventDTO> execute(SourceConfig sourceConfig) {
      log.info("ExecDevBy...");
      return null;
    }
}
