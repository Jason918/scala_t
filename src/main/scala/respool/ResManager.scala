package respool

import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import akka.event.Logging

/**
  * Created by Jason on 2015/11/15.
  */
case class Res[T](name: String, value: T, update: T => T)
case object Start
class ResManager extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case Start => log.info("Start")
    case Res(name: String, value, update) => {
      val res = context actorOf Props(classOf[Resource[_]], name, value, update)
      res ! Start
      res ! Update
    }
    case msg =>
      log.warning("unhandled msg:" + msg)
  }
}

