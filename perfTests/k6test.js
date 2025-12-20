import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 40,           // кількість паралельних "користувачів"
  duration: '30s',   // тривалість тесту
};

const cities = [
  'Chicago',
  'New York',
  'Los Angeles',
  'Houston',
  'Phoenix',
];

export default function () {
  // кожен VU бере місто по черзі
  let city = cities[__VU % cities.length];
  city = encodeURIComponent(city);

  const url = `http://localhost:8079/rentals/rent?city=${city}`;
  const res = http.get(url);

  if (res.status !== 200) {
    console.log(`VU ${__VU} | city=${city} | status=${res.status}`);
  }

  check(res, {
    'status is 200': (r) => r.status === 200,
  });

  sleep(0.5); // пауза, щоб навантаження було реалістичним
}
