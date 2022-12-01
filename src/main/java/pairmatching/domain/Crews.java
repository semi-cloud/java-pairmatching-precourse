package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Crews {
    private final List<String> crews;

    public Crews(List<String> crews) {
        this.crews = crews;
    }

    public List<PairCrew> randomMatchCrew(Course course) {
        List<String> shuffledName = Randoms.shuffle(crews);
        List<PairCrew> pairCrews = new ArrayList<>();

        for (int i = 0 ; i < shuffledName.size() ; i += 2) {
            List<String> pair = shuffledName.subList(i, i + 2);
            pairCrews.add(new PairCrew(convertToPair(course, pair)));

            if (shuffledName.size() % 2 != 0 && i == shuffledName.size() - 3) {
                List<String> pair1 = shuffledName.subList(i, shuffledName.size());
                pairCrews.add(new PairCrew(convertToPair(course,pair1)));
            }
        }
        return pairCrews;
    }

    private List<Crew> convertToPair(Course course, List<String> crews) {
        return crews.stream()
                .map(name -> new Crew(course, name))
                .collect(Collectors.toList());
    }
}
