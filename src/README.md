# Tema 2 Apd
## Popecu Mihai Costel 334CD

### MyDispatcher

Pentru implementarea temei am folosit in clasa MyDispatcher in metoda
AddTask un switch care in functie de algoritmul dorit adauga task-ul in
coada hostului ce rezulta in fuctie de algoritmul dat.
Tipurile de algorit sunt: ROUND_ROBIN, SHORTEST_QUEUE, 
SIZE_INTERVAL_TASK_ASSIGNMENT, LEAST_WORK_LEFT.

RoundeRobin: Adauga task-ul pe masura ce vin in coada hostului (i + 1)%n, 
unde n este numarul de hosturi, iar i este indexul hostulul in care s-a adaugat ultiam data .

ShortestQueue: Adauga task-ul in coada hostului cu cea mai mica lungime.

SizeIntervalTaskAssignment: Adauga task-ul in coada hostului cu index-ul 
egal cu indexul tipului te task care se afla in eneumul TaskType.

LeastWorkLeft: Adauga task-ul in coada hostului cu cel mai mic tip de lucrat.


### MyHost

Pentru implementarea temei am folosit in clasa MyHost in metoda run 
un while dupa o variabila booleana care este true iar in metoda shutdown va fi trecuta pe false.
In interiorul while-ului verific daca am un task de executat, daca da memorez
timpul de start, cat timpt task-ul mai are de rulat atunci verific daca task -ul curent este preemptibil, si daca in coada mai exista task uri
daca da verific daca prioiritatea task-ului curent este mai mica decat a task-ului din coada,
daca da bag task-ul curent in coada si scot task-ul din coada si il rulez.
Dupa verificarea preemptibilitatii, setez timpul curent, iar apoi setez timpul ramas de rulare al
task ului curent.
Dupa terminarea while-ului verific daca taskul curent este null, daca nu este null
dau finish la task si il fac null.

Metoda getQueueSize returneaza lungimea cozii de task-uri + 1 daca exista un task in executie.

Metoda getWorkLeft returneaza timpul ramas de rulare ale task-urilor din coada + timpul ramas de rulare al task-ului curent daca exista un task in executie.

Pentru implemtarea cozii de task-uri am folosit o coada de tipul PriorityBlockingQueue<Task> cu un comparator
care compara dupa prioritatea task-ului, iar daca au aceeasi prioritate compara dupa timpul de start.
Am ales aceasta coada pentru ca este thread safe si pentru ca este o coada de prioritati, sortata dupa ce criterii am nevoie.

