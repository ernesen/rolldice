package com.avaloq.rolldices;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rundice {

    @GetMapping("/readme")
	public Map <Object, Object> rundie(){
		HashMap<Object, Object> map = new HashMap<>();
		map.put("invalid", "you must pupulate values for number_dice, number_roll and number_side");
		map.put("example", "/roll?number_roll=100&number_dice=10&number_side=6");
		return map;
	}

	@GetMapping("/roll")
	@Validated
	public Map <Object, Object> rundie(@RequestParam int number_dice, @RequestParam int number_roll, @RequestParam int number_side){

        Random randomNumbers = new Random();

        int die1 = 0;
		int die2 = 0;
		int die3 = 0;
		int die4 = 0;
		int die5 = 0;
		int die6 = 0;
		int die7 = 0;
		int die8 = 0;
		int die9 = 0;
		int die10 = 0;			

        if (number_dice > 10 || number_dice < 1){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("invalid", number_dice);
			map.put("value", "The number of dice can't be more than 10 and less than 1");
			return map;
		}

        if (number_side <= 3 || number_side > 6 || number_side < 1){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("invalid", number_side);
			map.put("value", "The sides of a dice must be at least 4 and not more than 6");
			return map;
		}

		if (number_roll < 1 || number_roll > 1000000){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("invalid", number_roll);
			map.put("value", "The number of rolls can't be less than 1 and not more than 1000000");
			return map;
		}

		int[] totals = new int[number_dice*number_side + 1];

        for (int index = 0; index < totals.length; index++){
            totals[index]=0;
		}
        for (int roll = 1; roll <= number_roll; roll++){

			die1 = 1 + randomNumbers.nextInt(number_side);
			die2 = 1 + randomNumbers.nextInt(number_side);
			die3 = 1 + randomNumbers.nextInt(number_side);
			die4 = 1 + randomNumbers.nextInt(number_side);
			die5 = 1 + randomNumbers.nextInt(number_side);
			die6 = 1 + randomNumbers.nextInt(number_side);
			die7 = 1 + randomNumbers.nextInt(number_side);
			die8 = 1 + randomNumbers.nextInt(number_side);
			die9 = 1 + randomNumbers.nextInt(number_side);
			die10 = 1 + randomNumbers.nextInt(number_side);

			switch (number_dice) {
				case 1:
				   totals[die1]++;
				   break;
				case 2:
				   totals[die1 + die2 ]++;
				   break;
				case 3:
				   totals[die1 + die2 + die3]++;
				   break;
				case 4:
				   totals[die1 + die2 + die3 + die4]++;
				   break;
				case 5:
				   totals[die1 + die2 + die3 + die4  + die5]++;
				   break;
				case 6:
				   totals[die1 + die2 + die3 + die4  + die5 + die6]++;
				   break;
				case 7:
				   totals[die1 + die2 + die3 + die4  + die5 + die6 + die7]++;
				   break;
				case 8:
				   totals[die1 + die2 + die3 + die4  + die5 + die6 + die7 + die8]++;
				   break;
				case 9:
				   totals[die1 + die2 + die3 + die4  + die5 + die6 + die7 + die8 + die9]++;
				   break;
				case 10:
				   totals[die1 + die2 + die3 + die4  + die5 + die6 + die7 + die8 + die9 + die10]++;
				   break;
			 }
        }

		HashMap<Object, Object> hashMap  = new LinkedHashMap<Object, Object>();
		hashMap.put("number_dice", number_dice);
		hashMap.put("number_roll", number_roll);
		hashMap.put("number_side", number_side);

		HashMap<String, Integer> map = new LinkedHashMap<>();

        for (int k = number_dice; k < totals.length; k++){
			map.put(String.valueOf(k), totals[k]);
        }
		hashMap.put("DiceRolled",map);
		return hashMap;
    }
}
