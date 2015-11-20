import akka.actor
import akka.actor.{Props, ActorSystem}
import respool.{Res, ResManager}

object Main {
  def main(args: Array[String]) = {
    println("hello world")
    val system = ActorSystem("test")
    val ResPool = system actorOf Props[ResManager]
    ResPool ! "start"
    for (i <- 1 to 10)
      ResPool ! Res[(Double,Double)]("Driver_"+i, (Math.random()*100.0,Math.random()*100.0),
        old => (old._1+Math.random()-0.5,old._2+Math.random()-0.5))
    for (i <- 1 to 15)
      ResPool ! Res[(Double,Double)]("Passenger_"+i, (100.0*i,100.0*i),  old => (old._1+1,old._2+1))

  }
}