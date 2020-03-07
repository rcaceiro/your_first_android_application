package eu.rcaceiro.yourfirstandroidapplication.model

class Score private constructor(private val scoreNumber: Int) {
    companion object {
        internal fun of(score: Int): Score? {
            if (score < 0) {
                return null
            }
            return Score(score)
        }
    }
}

val Int.score
    get() = Score.of(this)