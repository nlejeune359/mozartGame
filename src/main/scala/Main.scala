package upmc.akka.ppc

import akka.actor.{Props,  Actor,  ActorRef,  ActorSystem}

object Concert extends App {
  import ConductorActor._
  import ProviderActor._
  import PlayerActor._
  import DataBaseActor._

  println("starting Mozart's game")
  val system = ActorSystem ("MozartGame")
  
  val databaseActor = system.actorOf(Props[DataBaseActor], "DataBase")   
  val playerActor = system.actorOf(Props[PlayerActor], "Player")
  val conductorActor: ActorRef = system.actorOf(Props(new ConductorActor(system.actorOf(Props(new ProviderActor(databaseActor, conductorActor)), "Provider"), playerActor)), "Conductor")

  conductorActor ! ConductorActor.StartGame()
}


// la racine d ’ un group d ’ actors
// pour parametrer la structure de l ’ actor
// e.g . le contexte d ’ evaluation
// val actorProps = Props [ MyActor ]
// retourne un actorRef
// val actor = system . actorOf ( actorProps )