package tests;

import api.WeatherAPI;
import api.WeatherApiV2;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import validator.DataValidator;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class Tests {

    WeatherAPI weatherAPIv1;
    WeatherApiV2 weatherAPIv2;

    //v2 strings are at the end because they're too long
    final static String validV1 = "{\"_Meta\":{\"Id\":\"85590\",\"Type\":\"weather\",\"LastUpdate\":\"2024-01-06T10:00:00\",\"Source\":\"siag\",\"Reduced\":false,\"UpdateInfo\":null},\"Self\":\"Weather/85590\",\"Id\":85590,\"date\":\"2024-01-06T10:00:00\",\"evolutiontitle\":null,\"evolution\":null,\"language\":null,\"Conditions\":[],\"Forecast\":[],\"Mountain\":[],\"Stationdata\":[{\"date\":\"2024-01-06T00:00:00\",\"Id\":3,\"CityName\":\"Bolzano / Bozen\",\"WeatherCode\":\"j\",\"WeatherDesc\":\"Dull, slightly rain\",\"WeatherImgUrl\":\"http://daten.buergernetz.bz.it/services/weather/graphics/icons/imgsource/wetter/icon_10.png\",\"Weathercode\":\"j\",\"Weatherdesc\":\"Dull, slightly rain\",\"WeatherImgurl\":\"http://daten.buergernetz.bz.it/services/weather/graphics/icons/imgsource/wetter/icon_10.png\",\"MinTemp\":0,\"Maxtemp\":6,\"MaxTemp\":6},{\"date\":\"2024-01-07T00:00:00\",\"Id\":3,\"CityName\":\"Bolzano / Bozen\",\"WeatherCode\":\"e\",\"WeatherDesc\":\"Dull\",\"WeatherImgUrl\":\"http://daten.buergernetz.bz.it/services/weather/graphics/icons/imgsource/wetter/icon_5.png\",\"Weathercode\":\"e\",\"Weatherdesc\":\"Dull\",\"WeatherImgurl\":\"http://daten.buergernetz.bz.it/services/weather/graphics/icons/imgsource/wetter/icon_5.png\",\"MinTemp\":2,\"Maxtemp\":8,\"MaxTemp\":8}],\"LicenseInfo\":{\"License\":\"CC0\",\"LicenseHolder\":\"https://provinz.bz.it/wetter\",\"Author\":\"\",\"ClosedData\":false}}";
    @Test
    public void apisShouldReturnValidData(){
        //v1
        assertNotNull("Weather API data should not be null", weatherAPIv1.getData());
        assertFalse("Weather API data should not be empty", weatherAPIv1.getData().isEmpty());
        //raw
        assertNotNull("Weather API data should not be null", weatherAPIv1.getData(validV1));
        assertFalse("Weather API data should not be empty", weatherAPIv1.getData(validV1).isEmpty());

        //v2
        assertNotNull("Weather API data should not be null", weatherAPIv2.getData());
        assertFalse("Weather API data should not be empty", weatherAPIv2.getData().isEmpty());
        //raw
        assertNotNull("Weather API data should not be null", weatherAPIv2.getData(validV2));
        assertFalse("Weather API data should not be empty", weatherAPIv2.getData(validV2).isEmpty());
    }

    @Test
    public void validDataShouldReturnTrue(){
        //v1
        String timestampField = "date";
        String[] values = {"MinTemp","Maxtemp","MaxTemp"};
        double valueAllowancePerDAY = 10.0;
        List<JSONObject> data = weatherAPIv1.getData(validV1);

        boolean isDataValid = DataValidator.validateData(
                data,
                timestampField,
                values,
                valueAllowancePerDAY
        );

        assertTrue(isDataValid);

        //v2
        String[] values2 = {"mvalue"};
        timestampField = "mvalidtime";
        List<List<JSONObject>> data2 = weatherAPIv2.getData(validV2);

        for(List<JSONObject> x : data2) {
            isDataValid = DataValidator.validateData(
                    x,
                    timestampField,
                    values2,
                    valueAllowancePerDAY
            );

            assertTrue(isDataValid);
        }
    }

    @Before
    public void initializeApiClasses(){
        weatherAPIv1 = new WeatherAPI();
        weatherAPIv2 = new WeatherApiV2();
    }


    final static String validV2 = "{\n" +
            "  \"offset\": 0,\n" +
            "  \"data\": {\n" +
            "    \"MeteoStation\": {\n" +
            "      \"stations\": {\n" +
            "        \"00390SF\": {\n" +
            "          \"sdatatypes\": {\n" +
            "            \"air-temperature\": {\n" +
            "              \"tmeasurements\": [\n" +
            "                {\n" +
            "                  \"mperiod\": 1800,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"2.0.0\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-03-18 09:50:08.763+0000\",\n" +
            "                  \"mvalidtime\": \"2022-03-18 08:50:00.000+0000\",\n" +
            "                  \"mvalue\": 1.5\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 31536000,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:00:04.070+0000\",\n" +
            "                  \"mvalidtime\": \"2023-12-01 00:00:00.000+0000\",\n" +
            "                  \"mvalue\": -5.5\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 3600,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"2.0.0\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-03-22 12:30:09.008+0000\",\n" +
            "                  \"mvalidtime\": \"2022-03-22 11:30:00.000+0000\",\n" +
            "                  \"mvalue\": 4.7\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 86400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"894a43218e58b711e88b4c85c8163ab3d1ef4741\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:28:12.230+0000\",\n" +
            "                  \"mvalidtime\": \"2021-12-31 12:00:00.000+0000\",\n" +
            "                  \"mvalue\": 1.1\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 600,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2016-07-13 16:24:17.238+0000\",\n" +
            "                  \"mvalidtime\": \"2024-01-06 21:30:00.000+0000\",\n" +
            "                  \"mvalue\": -6.7\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 1200,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2021-08-12 10:30:09.052+0000\",\n" +
            "                  \"mvalidtime\": \"2023-11-06 16:10:00.000+0000\",\n" +
            "                  \"mvalue\": -2.6\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 185400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"2.0.0\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-03-16 11:00:08.605+0000\",\n" +
            "                  \"mvalidtime\": \"2022-03-16 10:00:00.000+0000\",\n" +
            "                  \"mvalue\": 8\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"00700WS\": {\n" +
            "          \"sdatatypes\": {\n" +
            "            \"air-temperature\": {\n" +
            "              \"tmeasurements\": [\n" +
            "                {\n" +
            "                  \"mperiod\": 86400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"894a43218e58b711e88b4c85c8163ab3d1ef4741\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:12:05.291+0000\",\n" +
            "                  \"mvalidtime\": \"2021-12-31 12:00:00.000+0000\",\n" +
            "                  \"mvalue\": -0.9\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 31536000,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:00:04.221+0000\",\n" +
            "                  \"mvalidtime\": \"2023-12-01 00:00:00.000+0000\",\n" +
            "                  \"mvalue\": -8.9\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 600,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2016-07-13 16:21:38.372+0000\",\n" +
            "                  \"mvalidtime\": \"2024-01-06 21:30:00.000+0000\",\n" +
            "                  \"mvalue\": -12.7\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 1200,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"bd7ada46c649a05b7bc441e6b90bdef52f8e6b0a\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-11-29 12:10:04.743+0000\",\n" +
            "                  \"mvalidtime\": \"2023-05-17 20:20:00.000+0000\",\n" +
            "                  \"mvalue\": -6.6\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"02200MS\": {\n" +
            "          \"sdatatypes\": {\n" +
            "            \"air-temperature\": {\n" +
            "              \"tmeasurements\": [\n" +
            "                {\n" +
            "                  \"mperiod\": 1200,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2021-10-28 12:20:56.381+0000\",\n" +
            "                  \"mvalidtime\": \"2023-11-06 16:10:00.000+0000\",\n" +
            "                  \"mvalue\": 1.2\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 600,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2016-07-13 16:20:56.783+0000\",\n" +
            "                  \"mvalidtime\": \"2024-01-06 21:30:00.000+0000\",\n" +
            "                  \"mvalue\": -2\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 31536000,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:00:04.330+0000\",\n" +
            "                  \"mvalidtime\": \"2023-12-01 00:00:00.000+0000\",\n" +
            "                  \"mvalue\": -3.4\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 8400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"bd7ada46c649a05b7bc441e6b90bdef52f8e6b0a\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2023-03-20 10:40:55.599+0000\",\n" +
            "                  \"mvalidtime\": \"2023-03-20 09:40:00.000+0000\",\n" +
            "                  \"mvalue\": 6.2\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 86400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"894a43218e58b711e88b4c85c8163ab3d1ef4741\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:29:36.378+0000\",\n" +
            "                  \"mvalidtime\": \"2021-12-31 12:00:00.000+0000\",\n" +
            "                  \"mvalue\": 3\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 1800,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"e5e30561d496f968a095ebce0248080060b6cb67\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-11-25 09:10:58.904+0000\",\n" +
            "                  \"mvalidtime\": \"2022-11-30 08:00:00.000+0000\",\n" +
            "                  \"mvalue\": -0.6\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"02500MS\": {\n" +
            "          \"sdatatypes\": {\n" +
            "            \"air-temperature\": {\n" +
            "              \"tmeasurements\": [\n" +
            "                {\n" +
            "                  \"mperiod\": 31536000,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:00:04.639+0000\",\n" +
            "                  \"mvalidtime\": \"2023-12-01 00:00:00.000+0000\",\n" +
            "                  \"mvalue\": -0.9\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 8400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"bd7ada46c649a05b7bc441e6b90bdef52f8e6b0a\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2023-03-20 10:41:04.316+0000\",\n" +
            "                  \"mvalidtime\": \"2023-03-20 09:40:00.000+0000\",\n" +
            "                  \"mvalue\": 9.4\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 1800,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"e5e30561d496f968a095ebce0248080060b6cb67\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-11-25 09:11:06.721+0000\",\n" +
            "                  \"mvalidtime\": \"2022-11-30 08:00:00.000+0000\",\n" +
            "                  \"mvalue\": 1.2\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 86400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"EURAC\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-eurac\",\n" +
            "                    \"prversion\": \"894a43218e58b711e88b4c85c8163ab3d1ef4741\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2022-08-03 12:15:25.533+0000\",\n" +
            "                  \"mvalidtime\": \"2021-12-31 12:00:00.000+0000\",\n" +
            "                  \"mvalue\": 10.4\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 1200,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2021-06-01 01:40:55.419+0000\",\n" +
            "                  \"mvalidtime\": \"2023-11-06 16:10:00.000+0000\",\n" +
            "                  \"mvalue\": 3.3\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 2400,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"2.0.0\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2021-07-17 20:01:02.244+0000\",\n" +
            "                  \"mvalidtime\": \"2021-07-17 17:20:00.000+0000\",\n" +
            "                  \"mvalue\": 15.9\n" +
            "                },\n" +
            "                {\n" +
            "                  \"mperiod\": 600,\n" +
            "                  \"mprovenance\": {\n" +
            "                    \"prlineage\": \"SIAG\",\n" +
            "                    \"prname\": \"odh-mobility-dc-meteorology-bz\",\n" +
            "                    \"prversion\": \"9182181b78067621406c0c8fa7c0de1954ca8f2e\"\n" +
            "                  },\n" +
            "                  \"mtransactiontime\": \"2016-07-13 16:21:21.103+0000\",\n" +
            "                  \"mvalidtime\": \"2024-01-06 21:30:00.000+0000\",\n" +
            "                  \"mvalue\": 0\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"limit\": 200\n" +
            "}";
}
