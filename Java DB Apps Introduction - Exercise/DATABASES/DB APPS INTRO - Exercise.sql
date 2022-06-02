#Exercise --2--
SELECT 
    v.`name`, COUNT(mv.`minion_id`) AS `count`
FROM
    villains AS v
        JOIN
    minions_villains AS mv ON v.id = mv.villain_id
GROUP BY v.name
HAVING count > 15
ORDER BY count DESC;

#Exercise --3-- 
 SELECT m.`name`, m.`age`  from minions  as m 
 JOIN minions_villains as mv ON m.id = mv.minion_id
 WHERE mv.villain_id = 3; 
 
 

