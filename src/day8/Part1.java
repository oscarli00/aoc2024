package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    private final File file = new File(getClass().getResource("input.txt").getPath());
    private final char[][] map = new char[50][];
    private final Map<Character, List<int[]>> antennae = new HashMap<>();

    public Part1() throws FileNotFoundException {
        var scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            map[i] = line.toCharArray();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c != '.') {
                    if (!antennae.containsKey(c)) {
                        antennae.put(c, new ArrayList<>());
                    }
                    antennae.get(c).add(new int[]{i, j});
                }
            }
            i++;
        }

        int ans = 0;
        var visited = new boolean[map.length][map[0].length];
        for (var positions : antennae.values()) {
            for (i = 0; i < positions.size() - 1; i++) {
                var pos1 = positions.get(i);
                for (int j = i + 1; j < positions.size(); j++) {
                    var pos2 = positions.get(j);
                    var di = pos2[0] - pos1[0];
                    var dj = pos2[1] - pos1[1];
                    var newPos1 = new int[]{pos1[0] - di, pos1[0] - dj};
                    if (newPos1[0] >= 0 && newPos1[1] >= 0
                            && newPos1[0] < map.length && newPos1[1] < map[0].length
                            && !visited[newPos1[0]][newPos1[1]]) {
                        ans++;
                        visited[newPos1[0]][newPos1[1]] = true;
                    }
                    var newPos2 = new int[]{pos2[0] + di, pos2[0] + dj};
                    if (newPos2[0] >= 0 && newPos2[1] >= 0
                            && newPos2[0] < map.length && newPos2[1] < map[0].length
                            && !visited[newPos2[0]][newPos2[1]]) {
                        ans++;
                        visited[newPos2[0]][newPos2[1]] = true;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
