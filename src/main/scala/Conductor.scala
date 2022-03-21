package upmc.akka.ppc

import akka.actor.{ Props , Actor , ActorSystem, ActorRef }

object ConductorActor {
    case class StartGame()
    case class Measure(chords: List[DataBaseActor.Chord])
}

class ConductorActor (providerRef: ActorRef, playerRef: ActorRef) extends Actor {
    val random = new scala.util.Random

    def getNum(): Int = {
        return random.nextInt(11)
    }

    def receive = {
        case ConductorActor.StartGame() => providerRef ! ProviderActor.GetMeasure(getNum())
        case ConductorActor.Measure(chords) => {
            playerRef ! PlayerActor.Measure(chords) 
            Thread.sleep(1800)
            self ! ConductorActor.StartGame()
        }
    }
}