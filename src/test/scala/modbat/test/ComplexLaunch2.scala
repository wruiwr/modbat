package modbat.test

import modbat.dsl._

object ComplexLaunch2 {
  @Init def init { System.out.println("init") }
  @Shutdown def shutdown { System.out.println("shutdown") }
}

class ComplexLaunch2 (var id: Int) extends Model {
  def this() = this(0)

  @Before def before { System.out.println("before") }
  @After def after { System.out.println("after") }

  // transitions
  "init" -> "active" := skip
  "active" -> "active" := {
    id = id + 1
    launch(new Child2(id))
  }
  "active" -> "active" := {
    require(id < 2)
    launch(new ComplexLaunch2(id))
  }
  "active" -> "done" := {
    assert (id < 3)
  }
}
