var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb) {

    // 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
    //    i ponawia probe itd.

    var fork = this;
    var timeToWait = 1

    var tryAcquire = function() {
        if (fork.state === 0) {
            fork.state = 1;
            cb();
        } else { 
            timeToWait *= 2;
            setTimeout(tryAcquire, timeToWait);
        }
    }

    setTimeout(tryAcquire, timeToWait);
}

Fork.prototype.release = function() { 
    this.state = 0; 
}

var Philosopher = function(id, forks, conductor) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    this.conductor = conductor;
    return this;
}

Philosopher.prototype.startNaive = function(count) {
    var forks = this.forks,
    f1 = this.f1,
    f2 = this.f2,
    id = this.id;

    // zaimplementuj rozwiazanie naiwne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    var doCycle = function(no_cycles_left) {
        console.log("Philosopher " + id + " getting left fork");
        forks[f1].acquire( function () {
            console.log("Philosopher " + id + " getting right fork");
            forks[f2].acquire( function () {
                console.log("Philosopher " + id + " eating");
                setTimeout(function () {
                    forks[f1].release();
                    forks[f2].release();
                    console.log("Philosopher " + id + " released forks");
                    if (no_cycles_left-1 > 0) doCycle(no_cycles_left-1);
                }, Math.floor(Math.random() * 100) + 1);
            })
        });
    }

    doCycle(count);
}

Philosopher.prototype.startAsym = function(count) {
    var forks = this.forks,
    f1 = this.f1,
    f2 = this.f2,
    id = this.id;
    
    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    var oddCycle = function(no_cycles_left) {
        console.log("Philosopher " + id + " getting left fork");
        forks[f1].acquire( function () {
            console.log("Philosopher " + id + " getting right fork");
            forks[f2].acquire( function () {
                console.log("Philosopher " + id + " eating");
                setTimeout(function () {
                    forks[f1].release();
                    forks[f2].release();
                    console.log("Philosopher " + id + " released forks");
                    if (no_cycles_left-1 > 0) oddCycle(no_cycles_left-1);
                }, Math.floor(Math.random() * 100) + 1);
            })
        });
    }

    var evenCycle = function(no_cycles_left) {
        console.log("Philosopher " + id + " getting right fork");
        forks[f2].acquire( function () {
            console.log("Philosopher " + id + " getting left fork");
            forks[f1].acquire( function () {
                console.log("Philosopher " + id + " eating");
                setTimeout(function () {
                    forks[f1].release();
                    forks[f2].release();
                    console.log("Philosopher " + id + " released forks");
                    if (no_cycles_left-1 > 0) evenCycle(no_cycles_left-1);
                }, Math.floor(Math.random() * 100) + 1);
            })
        });
    }

    if (id % 2) {
        oddCycle(count);
    } else {
        evenCycle(count);
    }
}

var Conductor = function(N) {
    this.N = N;
    this.state = 0;
    return this;
}

Conductor.prototype.acquire = function(cb) {
    var conductor = this;
    var timeToWait = 1

    var tryAcquire = function() {
        if (conductor.state+1 < conductor.N) {
            conductor.state++;
            cb();
        } else { 
            timeToWait *= 2;
            setTimeout(tryAcquire, timeToWait);
        }
    }

    setTimeout(tryAcquire, timeToWait);
}

Conductor.prototype.release = function() { 
    this.state--; 
}

Philosopher.prototype.startConductor = function(count) {
    var forks = this.forks,
    f1 = this.f1,
    f2 = this.f2,
    id = this.id,
    conductor = this.conductor;
    
    // zaimplementuj rozwiazanie z kelnerem
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    var doCycle = function(no_cycles_left) {
        conductor.acquire( function() {
            console.log("Philosopher " + id + " getting left fork");
            forks[f1].acquire( function () {
                console.log("Philosopher " + id + " getting right fork");
                forks[f2].acquire( function () {
                    console.log("Philosopher " + id + " eating");
                    setTimeout(function () {
                        forks[f1].release();
                        forks[f2].release();
                        console.log("Philosopher " + id + " released forks");
                        conductor.release();
                        if (no_cycles_left-1 > 0) doCycle(no_cycles_left-1);
                    }, Math.floor(Math.random() * 100) + 1);
                })
            });
        });
    }

    doCycle(count);
}


var N = 5;
var forks = [];
var philosophers = [];
var conductor = new Conductor(N);
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks, conductor));
}

for (var i = 0; i < N; i++) {
    // philosophers[i].startNaive(10);
    // philosophers[i].startAsym(10);
    philosophers[i].startConductor(10);
}
