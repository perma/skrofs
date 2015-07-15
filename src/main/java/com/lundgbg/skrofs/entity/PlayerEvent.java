package com.lundgbg.skrofs.entity;

import javax.validation.constraints.Min;

public class PlayerEvent {

    @Min(1)
    private long playerId;
    @Min(1)
    private long eventId;
    private int round3;
    private int round4;
    private int round5;
    private int round6;
    private int round7;
    private int round8;
    private int round9;
    private int round10;
    private int round11;
    private int round12;
    private int round13;
    private int round14;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(final long playerId) {
        this.playerId = playerId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(final long eventId) {
        this.eventId = eventId;
    }

    public int getRound3() {
        return round3;
    }

    public void setRound3(final int round3) {
        this.round3 = round3;
    }

    public int getRound4() {
        return round4;
    }

    public void setRound4(final int round4) {
        this.round4 = round4;
    }

    public int getRound5() {
        return round5;
    }

    public void setRound5(final int round5) {
        this.round5 = round5;
    }

    public int getRound6() {
        return round6;
    }

    public void setRound6(final int round6) {
        this.round6 = round6;
    }

    public int getRound7() {
        return round7;
    }

    public void setRound7(final int round7) {
        this.round7 = round7;
    }

    public int getRound8() {
        return round8;
    }

    public void setRound8(final int round8) {
        this.round8 = round8;
    }

    public int getRound9() {
        return round9;
    }

    public void setRound9(final int round9) {
        this.round9 = round9;
    }

    public int getRound10() {
        return round10;
    }

    public void setRound10(final int round10) {
        this.round10 = round10;
    }

    public int getRound11() {
        return round11;
    }

    public void setRound11(final int round11) {
        this.round11 = round11;
    }

    public int getRound12() {
        return round12;
    }

    public void setRound12(final int round12) {
        this.round12 = round12;
    }

    public int getRound13() {
        return round13;
    }

    public void setRound13(final int round13) {
        this.round13 = round13;
    }

    public int getRound14() {
        return round14;
    }

    public void setRound14(final int round14) {
        this.round14 = round14;
    }

}
