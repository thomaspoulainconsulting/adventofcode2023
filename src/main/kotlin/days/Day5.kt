package days

import java.math.BigInteger

class Day5 : Day(5, "If You Give A Seed A Fertilizer") {
    override fun solvePart1(input: List<String>): String {
        val seeds = input.first().replace("seeds: ", "").split(" ").map { it.toBigInteger() }
        var seedToSoilMap = mutableMapOf<BigInteger, BigInteger>()
        var soilToFertilizerMap = mutableMapOf<BigInteger, BigInteger>()
        var fertilizerToWaterMap = mutableMapOf<BigInteger, BigInteger>()
        var waterToLightMap = mutableMapOf<BigInteger, BigInteger>()
        var lightToTemperatureMap = mutableMapOf<BigInteger, BigInteger>()
        var temperatureToHumidityMap = mutableMapOf<BigInteger, BigInteger>()
        var humidityToLocationMap = mutableMapOf<BigInteger, BigInteger>()

        var isSeedToSoil = false
        var isSoilToFertilizer = false
        var isFertilizerToWater = false
        var isWaterToLight = false
        var isLightToTemperature = false
        var isTemperatureToHumidity = false
        var isHumidityToLocation = false

        input.slice(2..<input.size)
            .forEach { line ->

                when (line) {
                    "seed-to-soil map:" -> {
                        isSeedToSoil = true
                        return@forEach
                    }

                    "soil-to-fertilizer map:" -> {
                        isSoilToFertilizer = true
                        return@forEach
                    }

                    "fertilizer-to-water map:" -> {
                        isFertilizerToWater = true
                        return@forEach
                    }

                    "water-to-light map:" -> {
                        isWaterToLight = true
                        return@forEach
                    }

                    "light-to-temperature map:" -> {
                        isLightToTemperature = true
                        return@forEach
                    }

                    "temperature-to-humidity map:" -> {
                        isTemperatureToHumidity = true
                        return@forEach
                    }

                    "humidity-to-location map:" -> {
                        isHumidityToLocation = true
                        return@forEach
                    }

                    "" -> {
                        if (isHumidityToLocation) {
                            // find location
                            val locations = mutableListOf<BigInteger>()
                            seeds.forEach { seed ->
                                val soil = seedToSoilMap.toSortedMap()[seed] ?: seed
                                val fertilizer = soilToFertilizerMap.toSortedMap().filter { it.key == soil }.values.firstOrNull() ?: soil
                                val water = fertilizerToWaterMap.toSortedMap().filter { it.key == fertilizer }.values.firstOrNull() ?: fertilizer
                                val light = waterToLightMap.toSortedMap().filter { it.key == water }.values.firstOrNull() ?: water
                                val temperature = lightToTemperatureMap.toSortedMap().filter { it.key == light }.values.firstOrNull() ?: light
                                val humidity = temperatureToHumidityMap.toSortedMap().filter { it.key == temperature }.values.firstOrNull() ?: temperature
                                val location = humidityToLocationMap.toSortedMap().filter { it.key == humidity }.values.firstOrNull() ?: humidity
                                locations.add(location)
                            }
                            return locations.min().toString()
                        }


                        isSeedToSoil = false
                        isSoilToFertilizer = false
                        isFertilizerToWater = false
                        isWaterToLight = false
                        isLightToTemperature = false
                        isTemperatureToHumidity = false
                        isHumidityToLocation = false
                    }
                }

                if (isSeedToSoil) {
                    seedToSoilMap = extractedValues(line, seedToSoilMap)
                }
                if (isSoilToFertilizer) {
                    soilToFertilizerMap = extractedValues(line, soilToFertilizerMap)
                }
                if (isFertilizerToWater) {
                    fertilizerToWaterMap = extractedValues(line, fertilizerToWaterMap)
                }
                if (isWaterToLight) {
                    waterToLightMap = extractedValues(line, waterToLightMap)
                }
                if (isLightToTemperature) {
                    lightToTemperatureMap = extractedValues(line, lightToTemperatureMap)
                }
                if (isTemperatureToHumidity) {
                    temperatureToHumidityMap = extractedValues(line, temperatureToHumidityMap)
                }
                if (isHumidityToLocation) {
                    humidityToLocationMap = extractedValues(line, humidityToLocationMap)
                }
            }

        return ""
    }

    private fun extractedValues(line: String, map: MutableMap<BigInteger, BigInteger>): MutableMap<BigInteger, BigInteger> {
        line.split(" ")
            .map { it.toBigInteger() }
            .let {
                for (i in BigInteger.ZERO..it[2]) {
                    map.put(it[1] + i, it[0] + i)
                }
            }
        return map
    }

    operator fun BigInteger.rangeTo(other: BigInteger) =
        BigIntegerRange(this, other)

    class BigIntegerRange(
        override val start: BigInteger,
        override val endInclusive: BigInteger
    ) : ClosedRange<BigInteger>, Iterable<BigInteger> {
        override operator fun iterator(): Iterator<BigInteger> =
            BigIntegerRangeIterator(this)
    }

    class BigIntegerRangeIterator(
        private val range: ClosedRange<BigInteger>
    ) : Iterator<BigInteger> {
        private var current = range.start

        override fun hasNext(): Boolean =
            current <= range.endInclusive

        override fun next(): BigInteger {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            return current++
        }
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}

