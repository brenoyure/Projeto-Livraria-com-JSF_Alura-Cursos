package br.com.caelum.livraria.util;

import static javax.faces.event.PhaseId.ANY_PHASE;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("FASE: " + event.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return ANY_PHASE;
	}

}
