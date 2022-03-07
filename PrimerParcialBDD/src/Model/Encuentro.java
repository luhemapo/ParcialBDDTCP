package Model;

public class Encuentro {
	
	public int consecutivo;
	public String equipoLocal;
	public String equipoVisitante;
	public String fecha;
	public String tipoDeporte;
	
	public Encuentro(int consecutivo, String equipoLocal, 
			String equipoVisitante, String fecha, String tipoDeporte) {
		
		this.consecutivo = consecutivo;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = fecha;
		this.tipoDeporte = tipoDeporte;
		
	}
	
	public String[] getEncientro() {
		
		String [] auxEncuentro = {getConsecutivo()+"", getEquipoLocal(), 
				getEquipoVisitante(), getFecha(), getTipoDeporte()};
		
		return auxEncuentro;
		
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipoDeporte() {
		return tipoDeporte;
	}

	public void setTipoDeporte(String tipoDeporte) {
		this.tipoDeporte = tipoDeporte;
	}
	
}
