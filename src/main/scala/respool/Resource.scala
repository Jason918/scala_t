package respool

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by Jason on 2015/11/15.
  */
case object Update

class Resource[T](name:String, var value:T, update:T=>T) extends Actor {
  val log = Logging(context.system, this)
  override def receive: Actor.Receive = {
    case Start => log.info(name + " started")
    case Update => {
      log.info("old value of {} is {}", name, value)
      value = update(value)
      log.info("new value of {} is {}", name, value)
    }
    case "get" => sender ! value
    case _ => log.info("unknown msg");
  }
}