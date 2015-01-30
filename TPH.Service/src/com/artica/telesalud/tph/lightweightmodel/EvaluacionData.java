package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

public class EvaluacionData implements Serializable{

        public static final Integer VIA_AEREA_PERMEABLE = 1,
                                   VIA_AEREA_OBSTRUIDA = 2,
                                   RESPIRACION_AUSENTE = 3,
                                   RESPIRACION_DIFICULTOSA = 4,
                                   RESPIRACION_NORMAL = 5,
                                   RESPIRACION_SUPERFICIAL = 6,
                                   PULSO_PRESENTE = 7,
                                   PULSO_AUSENTE = 8,
                                   PULSO_RITMICO = 9,
                                   PULSO_ARITMICO = 10,
                                   PULSO_FUERTE = 11,
                                   PULSO_DEBIL = 12,
                                   NIVEL_RESPUESTA_ALERTA = 13,
                                   NIVEL_RESPUESTA_LLAMADO = 14,
                                   NIVEL_RESPUESTA_DOLOR = 15,
                                   NIVEL_RESPUESTA_SIN_RESPUESTA = 16,
                                   EMERGENCIA_PARO_CARDIACO = 17,
                                   EMERGENCIA_NEUROLOGIA = 18,
                                   EMERGENCIA_ORGANOS_SENTIDOS = 19,
                                   EMERGENCIA_CARDIOVASCULAR = 20,
                                   EMERGENCIA_GASTROINTESTINAL = 21,
                                   EMERGENCIA_GENITOURINARIO = 22,
                                   EMERGENCIA_GINECOOBSTETRICO = 23,
                                   EMERGENCIA_SHOCK = 24,
                                   EMERGENCIA_INTOXICACION = 25,
                                   EMERGENCIA_SIQUIATRICA = 26,
                                   EMERGENCIA_OVACE = 27,
                                   EMERGENCIA_TERMICA = 28,
                                   EMERGENCIA_ENFERMEDAD_COMUN = 29,
                                   PRIORIDAD_TRIAGE_AMARILLO = 30,
                                   PRIORIDAD_TRIAGE_ROJO = 31,
                                   PRIORIDAD_TRIAGE_NEGRO = 32,
                                   PRIORIDAD_TRIAGE_VERDE = 33,
                                   PRIORIDAD_TRIAGE_BLANCO = 34;
       
       
        private Integer permeavilidadViaAerea;
        private String causaObstruccionVia;
        private Integer respiracion;
        private Integer pulso;
        private Integer ritmoPulso;
        private Integer fuerzaPulso;
        private Integer nivelRespuesta;
        private Boolean pielNormal;
        private Boolean pielPalida;
        private Boolean pielCaliente;
        private Boolean pielCianotica;
        private Boolean pielHumeda;
        private Boolean pielEnrojecida;
        private Boolean pielSeca;
        private Boolean pupilasMioticas;
        private Boolean pupilasMidriaticas;
        private Boolean pupilasArisocoricas;
        private Boolean pupilasIsocoricas;
        private Boolean pupilasReactivas;
        private Boolean pupilasNoReactivas;
        private Boolean pupilasNoEvaluables;
        private Boolean pupilasProtesisIzquierda;
        private Boolean pupilasProtesisDerecha;
        private Integer glasgowRO;
        private Integer glasgowRV;
        private Integer glasgowRM;
        private Integer tipoEmergencia;
        private Boolean politrauma;
        private Boolean tec;
        private Boolean maxilofacial;
        private Boolean raquimedular;
        private Boolean torax;
        private Boolean abdomen;
        private Boolean pelvico;
        private Boolean extremidadSuperior;
        private Boolean extremidadInferior;
        private Boolean tejidosBlandos;
        private Boolean osteomuscular;
        private Boolean organosSentidos;
        private Boolean otroTrauma;
        private String cualOtroTrauma;
        private Integer prioridadTriage;
       
        public Integer getPermeavilidadViaAerea() {
                return permeavilidadViaAerea;
        }
        public void setPermeavilidadViaAerea(Integer permeavilidadViaAerea) {
                this.permeavilidadViaAerea = permeavilidadViaAerea;
        }
        public String getCausaObstruccionVia() {
                return causaObstruccionVia;
        }
        public void setCausaObstruccionVia(String causaObstruccionVia) {
                this.causaObstruccionVia = causaObstruccionVia;
        }
        public Integer getRespiracion() {
                return respiracion;
        }
        public void setRespiracion(Integer respiracion) {
                this.respiracion = respiracion;
        }
        public Integer getPulso() {
                return pulso;
        }
        public void setPulso(Integer pulso) {
                this.pulso = pulso;
        }
        public Integer getRitmoPulso() {
                return ritmoPulso;
        }
        public void setRitmoPulso(Integer ritmoPulso) {
                this.ritmoPulso = ritmoPulso;
        }
        public Integer getFuerzaPulso() {
                return fuerzaPulso;
        }
        public void setFuerzaPulso(Integer fuerzaPulso) {
                this.fuerzaPulso = fuerzaPulso;
        }
        public Integer getNivelRespuesta() {
                return nivelRespuesta;
        }
        public void setNivelRespuesta(Integer nivelRespuesta) {
                this.nivelRespuesta = nivelRespuesta;
        }
        public Boolean getPielNormal() {
                return pielNormal;
        }
        public void setPielNormal(Boolean pielNormal) {
                this.pielNormal = pielNormal;
        }
        public Boolean getPielPalida() {
                return pielPalida;
        }
        public void setPielPalida(Boolean pielPalida) {
                this.pielPalida = pielPalida;
        }
        public Boolean getPielCaliente() {
                return pielCaliente;
        }
        public void setPielCaliente(Boolean pielCaliente) {
                this.pielCaliente = pielCaliente;
        }
        public Boolean getPielCianotica() {
                return pielCianotica;
        }
        public void setPielCianotica(Boolean pielCianotica) {
                this.pielCianotica = pielCianotica;
        }
        public Boolean getPielHumeda() {
                return pielHumeda;
        }
        public void setPielHumeda(Boolean pielHumeda) {
                this.pielHumeda = pielHumeda;
        }
        public Boolean getPielEnrojecida() {
                return pielEnrojecida;
        }
        public void setPielEnrojecida(Boolean pielEnrojecida) {
                this.pielEnrojecida = pielEnrojecida;
        }
        public Boolean getPielSeca() {
                return pielSeca;
        }
        public void setPielSeca(Boolean pielSeca) {
                this.pielSeca = pielSeca;
        }
        public Boolean getPupilasMioticas() {
                return pupilasMioticas;
        }
        public void setPupilasMioticas(Boolean pupilasMioticas) {
                this.pupilasMioticas = pupilasMioticas;
        }
        public Boolean getPupilasMidriaticas() {
                return pupilasMidriaticas;
        }
        public void setPupilasMidriaticas(Boolean pupilasMidriaticas) {
                this.pupilasMidriaticas = pupilasMidriaticas;
        }
        public Boolean getPupilasArisocoricas() {
                return pupilasArisocoricas;
        }
        public void setPupilasArisocoricas(Boolean pupilasArisocoricas) {
                this.pupilasArisocoricas = pupilasArisocoricas;
        }
        public Boolean getPupilasIsocoricas() {
                return pupilasIsocoricas;
        }
        public void setPupilasIsocoricas(Boolean pupilasIsocoricas) {
                this.pupilasIsocoricas = pupilasIsocoricas;
        }
        public Boolean getPupilasReactivas() {
                return pupilasReactivas;
        }
        public void setPupilasReactivas(Boolean pupilasReactivas) {
                this.pupilasReactivas = pupilasReactivas;
        }
        public Boolean getPupilasNoReactivas() {
                return pupilasNoReactivas;
        }
        public void setPupilasNoReactivas(Boolean pupilasNoReactivas) {
                this.pupilasNoReactivas = pupilasNoReactivas;
        }
        public Boolean getPupilasNoEvaluables() {
                return pupilasNoEvaluables;
        }
        public void setPupilasNoEvaluables(Boolean pupilasNoEvaluables) {
                this.pupilasNoEvaluables = pupilasNoEvaluables;
        }
        public Boolean getPupilasProtesisIzquierda() {
                return pupilasProtesisIzquierda;
        }
        public void setPupilasProtesisIzquierda(Boolean pupilasProtesisIzquierda) {
                this.pupilasProtesisIzquierda = pupilasProtesisIzquierda;
        }
        public Boolean getPupilasProtesisDerecha() {
                return pupilasProtesisDerecha;
        }
        public void setPupilasProtesisDerecha(Boolean pupilasProtesisDerecha) {
                this.pupilasProtesisDerecha = pupilasProtesisDerecha;
        }
        public Integer getGlasgowRO() {
                return glasgowRO;
        }
        public void setGlasgowRO(Integer glasgowRO) {
                this.glasgowRO = glasgowRO;
        }
        public Integer getGlasgowRV() {
                return glasgowRV;
        }
        public void setGlasgowRV(Integer glasgowRV) {
                this.glasgowRV = glasgowRV;
        }
        public Integer getGlasgowRM() {
                return glasgowRM;
        }
        public void setGlasgowRM(Integer glasgowRM) {
                this.glasgowRM = glasgowRM;
        }
        public Integer getTipoEmergencia() {
                return tipoEmergencia;
        }
        public void setTipoEmergencia(Integer tipoEmergencia) {
                this.tipoEmergencia = tipoEmergencia;
        }
        public Boolean getPolitrauma() {
                return politrauma;
        }
        public void setPolitrauma(Boolean politrauma) {
                this.politrauma = politrauma;
        }
        public Boolean getTec() {
                return tec;
        }
        public void setTec(Boolean tec) {
                this.tec = tec;
        }
        public Boolean getMaxilofacial() {
                return maxilofacial;
        }
        public void setMaxilofacial(Boolean maxilofacial) {
                this.maxilofacial = maxilofacial;
        }
        public Boolean getRaquimedular() {
                return raquimedular;
        }
        public void setRaquimedular(Boolean raquimedular) {
                this.raquimedular = raquimedular;
        }
        public Boolean getTorax() {
                return torax;
        }
        public void setTorax(Boolean torax) {
                this.torax = torax;
        }
        public Boolean getAbdomen() {
                return abdomen;
        }
        public void setAbdomen(Boolean abdomen) {
                this.abdomen = abdomen;
        }
        public Boolean getPelvico() {
                return pelvico;
        }
        public void setPelvico(Boolean pelvico) {
                this.pelvico = pelvico;
        }
        public Boolean getExtremidadSuperior() {
                return extremidadSuperior;
        }
        public void setExtremidadSuperior(Boolean extremidadSuperior) {
                this.extremidadSuperior = extremidadSuperior;
        }
        public Boolean getExtremidadInferior() {
                return extremidadInferior;
        }
        public void setExtremidadInferior(Boolean extremidadInferior) {
                this.extremidadInferior = extremidadInferior;
        }
        public Boolean getTejidosBlandos() {
                return tejidosBlandos;
        }
        public void setTejidosBlandos(Boolean tejidosBlandos) {
                this.tejidosBlandos = tejidosBlandos;
        }
        public Boolean getOsteomuscular() {
                return osteomuscular;
        }
        public void setOsteomuscular(Boolean osteomuscular) {
                this.osteomuscular = osteomuscular;
        }
        public Boolean getOrganosSentidos() {
                return organosSentidos;
        }
        public void setOrganosSentidos(Boolean organosSentidos) {
                this.organosSentidos = organosSentidos;
        }
        public Boolean getOtroTrauma() {
                return otroTrauma;
        }
        public void setOtroTrauma(Boolean otroTrauma) {
                this.otroTrauma = otroTrauma;
        }
        public String getCualOtroTrauma() {
                return cualOtroTrauma;
        }
        public void setCualOtroTrauma(String cualOtroTrauma) {
                this.cualOtroTrauma = cualOtroTrauma;
        }
        public Integer getPrioridadTriage() {
                return prioridadTriage;
        }
        public void setPrioridadTriage(Integer prioridadTriage) {
                this.prioridadTriage = prioridadTriage;
        }
}