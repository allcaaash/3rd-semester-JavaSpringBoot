package ru.ulanov.MyFirstTestAppSpringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    private static ArrayList<String> arrayList;
    private static HashMap<Integer, String> hashMap;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
        defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "arrayArg",
        defaultValue = "") String s) {
        if (arrayList == null)
            arrayList = new ArrayList<>();

        arrayList.add(s);
    }

    @GetMapping("/show-array")
    public ArrayList<String> showArrayList() {
        if (arrayList == null)
            arrayList = new ArrayList<>();

        return arrayList;
    }

    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "mapArg",
        defaultValue = "") String s) {
        if (hashMap == null)
            hashMap = new HashMap<>();

        int index = hashMap.size();
        hashMap.put(index, s);
    }

    @GetMapping("/show-map")
    public HashMap<Integer, String> showHasMap() {
        if(hashMap == null)
            hashMap = new HashMap<>();

        return hashMap;
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        if (arrayList == null)
            arrayList = new ArrayList<>();
        if (hashMap == null)
            hashMap = new HashMap<>();

        return String.format("ArrayList: %d\nHashMap: %d", arrayList.size(), hashMap.size());
    }
}
