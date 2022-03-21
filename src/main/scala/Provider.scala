package upmc.akka.ppc

import akka.actor.{ Props , Actor , ActorSystem, ActorRef }

object ProviderActor {
    case class StartGame()
    case class GetMeasure(num:Int)
}

class ProviderActor (databaseRef: ActorRef, conductorRef: ActorRef) extends Actor {
    import ProviderActor._

    var compteur = 0
    def receive = {
        case GetMeasure(num) => {
            var column = 0
            if(compteur > 7) {
                column = partie2(num)(compteur%8)
            } else {
                column = partie1(num)(compteur)
            }
            compteur = (compteur + 1) % 16

            databaseRef ! DataBaseActor.GetMeasure(column)
        }
        case DataBaseActor.Measure(chords: List[DataBaseActor.Chord]) => conductorRef ! ConductorActor.Measure(chords)  
    }

    var partie1 = Array.ofDim[Int](11, 8)
    partie1 = Array(Array(96, 22, 141, 41, 105, 122, 11, 30),
        Array(32, 6, 128, 63, 146, 46, 134, 81),
        Array(69, 95, 158, 13, 153, 55, 110, 24),
        Array (40, 17, 113, 85, 161, 2, 159, 100),
        Array(148, 74, 163, 45, 80, 97, 36, 107),
        Array(104, 157, 27, 167, 154, 68, 118, 91),
        Array(152, 60, 171, 53, 99, 133, 21, 127),
        Array(119, 84, 114, 50, 140, 86, 169, 94),
        Array(98, 142, 42, 156, 75, 129, 62, 123),
        Array(3, 87, 165, 61, 135, 47, 147, 33),
        Array(54, 130, 10, 103, 28, 37, 106, 5)
    )

    var partie2 = Array.ofDim[Int](11, 8)
    partie2 = Array(
        Array(70, 121, 26, 9, 112, 49, 109, 14),
        Array(117, 39, 126, 56, 174, 18, 116, 83),
        Array(66, 139, 15, 132, 73, 58, 145, 79),
        Array(90, 176, 7, 34, 67, 160, 52, 170),
        Array(25, 143, 64, 125, 76, 136, 1, 93),
        Array(138, 71, 150, 29, 101, 162, 23, 151),
        Array(16, 155, 57, 175, 43, 168, 89, 172),
        Array(120, 88, 48, 166, 51, 115, 72, 111),
        Array(65, 77, 19, 82, 137, 38, 149, 8),
        Array(102, 4, 31, 164, 144, 59, 173, 78),
        Array(35, 20, 108, 92, 12, 124, 44, 131)
    )
    

    // var partie1 = Array.ofDim[Int] (11,8) 
    // partie1 = Array(Array(95, 21, 140, 40, 104, 121, 10, 29),
    //                 Array(31, 5, 127, 62, 145, 45, 133, 80), 
    //                 Array(68, 94, 157, 12, 152, 54, 109, 23),
    //                 Array(39, 16, 112, 84, 160, 1, 158, 99), 
    //                 Array(147, 73, 162, 44, 79, 96, 35, 106), 
    //                 Array(103, 156, 26, 166, 153, 67, 117, 90),
    //                 Array(151, 59, 170, 52, 98, 132, 20, 126), 
    //                 Array(118, 83, 113, 49, 139, 85, 168, 93), 
    //                 Array(97, 141, 41, 155, 74, 128, 61, 122),
    //                 Array(2, 86, 164, 60, 134, 46, 146, 32), 
    //                 Array(53, 129, 9, 102, 27, 36, 105, 4))

    // var partie2 = Array.ofDim[Int] (11,8) 
    // partie2 = Array(Array(69, 120, 25, 8, 111, 48, 108, 13), 
    //                 Array(116, 38, 125, 55, 173, 17, 115, 82), 
    //                 Array(65, 138, 14, 131, 72, 57, 144, 78),
    //                 Array(89, 175, 6, 33, 66, 159, 51, 169), 
    //                 Array(24, 142, 63, 124, 75, 135, 0, 92), 
    //                 Array(137, 70, 149, 28, 100, 161, 22, 150),
    //                 Array(15, 154, 56, 174, 42, 167, 88, 171), 
    //                 Array(119, 87, 47, 165, 50, 114, 71, 110), 
    //                 Array(64, 76, 18, 81, 136, 37, 148, 7),
    //                 Array(101, 3, 30, 163, 143, 58, 172, 77), 
    //                 Array(34, 19, 107, 91, 11, 123, 43, 130))    
}
