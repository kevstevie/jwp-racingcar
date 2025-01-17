package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingGameController {

    private static final String DELIMITER = ",";

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public GameResultDto play(@RequestBody final PlayRequestDto playRequestDto) {
        final var names = Arrays.asList(playRequestDto.getNames().split(DELIMITER));
        return racingGameService.play(names, playRequestDto.getCount());
    }

    @GetMapping("/plays")
    public List<GameResultDto> findResult() {
        return racingGameService.findAllResult();
    }
}
