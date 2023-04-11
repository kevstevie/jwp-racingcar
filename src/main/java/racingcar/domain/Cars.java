package racingcar.domain;

import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final int ONLY_ONE_CAR = 1;

    private final List<Car> cars;

    public Cars(List<String> names) {
        validateDuplicatedName(names);
        validateSoloPlay(names);
        this.cars = names.stream().map(Car::new).collect(Collectors.toList());
    }

    public Cars(String[] names) {
        this(Arrays.asList(names));
    }

    private void validateSoloPlay(List<String> carNames) {
        if (carNames.size() == ONLY_ONE_CAR) {
            throw new IllegalArgumentException("[ERROR] 차를 둘 이상 입력하세요.");
        }
    }

    private void validateDuplicatedName(List<String> carNames) {
        int carsSize = carNames.size();
        int duplicateRemovedCount =
                (int) carNames.stream()
                        .map(String::trim)
                        .distinct()
                        .count();
        if (carsSize != duplicateRemovedCount) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 사용할 수 없습니다.");
        }
    }

    public void moveCars(NumberGenerator numberGenerator) {

        cars.forEach(car -> car.move(numberGenerator.generateNumber()));
    }

    public List<Car> getCars() {
        return cars;
    }
}